import React, {useEffect, useState} from "react"
import axios from 'axios';
import Cookies from "js-cookie";


const baseURL = "cart"
const csrfToken=  Cookies.get('XSRF-TOKEN');


function ShoppingCart(){

    const [cartItems, setCartItems] = useState([] );

    useEffect(() => {
        axios(`${baseURL}`, {
                method: 'GET',
            headers: {

                'X-XSRF-TOKEN': csrfToken
            }
            }
        ).then((response) => {
            setCartItems([response.data] )

        })
    }, [])

    const deleteFromCart = (id) => {
        console.log(id)
        axios(`cartitem`, {
                method: 'DELETE',
                params: {
                  id: id
                },
                headers: {

                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => {
            console.log("yo")
        })
    }




    return (
        <div className="Cart">
            <div>
            Your Shopping Cart:
                { cartItems.length !== 0 ?
                    <table>
                        <thead>
                        <tr>
                            <td>Product</td>
                            <td>Quantity</td>
                        </tr>
                        </thead>

                        {cartItems.map((items, i) => {
                            return (
                                <React.Fragment key={i}>
                                    <tbody>
                                        {items.map((subItems, sI) => {
                                            //return <li key={sI}> {subItems.product} + {subItems.quantity} </li>
                                            return(
                                                <tr key={sI}>
                                                    <td>{subItems.product}</td>
                                                    <td>{subItems.quantity}</td>
                                                    <td>
                                                        <button onClick={() => deleteFromCart(subItems.id)}> Delete </button>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                    </tbody>
                                </React.Fragment>
                            )
                        })
                        }
                    </table>
                : null}
            </div>
        </div>
    )


}

export default ShoppingCart;