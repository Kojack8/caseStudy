import React, {useEffect, useState} from "react"
import axios from 'axios';
import Cookies from "js-cookie";
import DeleteFromCartModal from "../deleteFromCartModal";
import PayWithCard from "../payWithCard";




const baseURL = "cart"
const csrfToken=  Cookies.get('XSRF-TOKEN');




const ShoppingCart = () => {

    const [cartItems, setCartItems] = useState([] );
    const [showModal, setShowModal] = useState(false);
    const [showPayWithCard, setShowPayWithCard] = useState(false);
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
            console.log(cartItems);

        })
        // eslint-disable-next-line
    }, [])

    const deleteFromCart = (item) => {
        setShowModal(true);
        setSelectedItem(item);

    }

    const modalCallBackData = (data) => {
        setShowModal(data);
    }

    const finalCheckOut = () => {
        setShowPayWithCard(false);
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
            {showPayWithCard ? <PayWithCard callBack={finalCheckOut}/> : null}

            <div>
                Your shopping cart:
                { cartItems.length === 0 || cartItems[0].length === 0 ? <div> Currently empty!</div> :
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


                                    <button onClick={() => setShowPayWithCard(true)}> Pay With Card </button>
                                </React.Fragment>
                            )
                        })}
                        <div>{cartItems[0].length}</div>
                    </table>}


            </div>
        </div>
    )


}

export default ShoppingCart;