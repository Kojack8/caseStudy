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
                                    {items.map((subItems, sI) => {
                                        //return <li key={sI}> {subItems.product} + {subItems.quantity} </li>
                                        return(
                                            <tr key={sI}>
                                                <td>{subItems.product}</td>
                                                <td>{subItems.quantity}</td>
                                            </tr>
                                        )
                                    })}
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