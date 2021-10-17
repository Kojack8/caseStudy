import React, {useEffect, useState} from "react"
import axios from 'axios';
import Cookies from "js-cookie";
import DeleteFromCartModal from "../deleteFromCartModal";


const baseURL = "cart"
const csrfToken=  Cookies.get('XSRF-TOKEN');


function ShoppingCart(){

    const [cartItems, setCartItems] = useState([] );
    const [showModal, setShowModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState([]);

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

    const deleteFromCart = (item) => {
        setShowModal(true);
        setSelectedItem(item);
        /*console.log(id)
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
        })*/
    }

    const modalCallBackData = (data) => {
        setShowModal(data);
    }

    const finalCheckOut = () => {
        axios(`purchase`, {
                method: 'POST',
                params: {
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
            {showModal ? <DeleteFromCartModal item={selectedItem} callBack={modalCallBackData} /> : null}
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
                                                        <button onClick={() => deleteFromCart(subItems)}> Remove From Cart </button>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                    </tbody>
                                    <button onClick={() => finalCheckOut()}> Check Out </button>
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