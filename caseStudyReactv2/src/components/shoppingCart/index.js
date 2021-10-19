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
    const [itemCount, setItemCount] = useState(0);
    const [totalPrice, setTotalPrice] = useState(0.00);

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
        // eslint-disable-next-line
    }, [])

    useEffect( () => {
        setTotals();
        // eslint-disable-next-line
    }, [cartItems])

    const setTotals = () => {
        cartItems.forEach(cartItem => {
            cartItem.forEach(item => {
                setItemCount((itemCount) => itemCount + parseInt(item.quantity));
                setTotalPrice((totalPrice) => totalPrice + (parseInt(item.quantity) * item.price));

            })})
// eslint-disable-next-line
    }

    const deleteFromCart = (item) => {
        setShowModal(true);
        setSelectedItem(item);

    }

    const modalCallBackData = (data) => {
        setShowModal(data);
    }

    const finalCheckOut = () => {
        setShowPayWithCard(false);

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
                            <td>Price</td>
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
                                                    <td>{subItems.productName}</td>
                                                    <td>{subItems.quantity}</td>
                                                    <td>{subItems.price}</td>
                                                    <td>
                                                        <button onClick={() => deleteFromCart(subItems)}> Remove From Cart </button>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                        <tr>
                                            <td>Total:</td>
                                            <td>{itemCount}</td>
                                            <td>{totalPrice}</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <button onClick={() => setShowPayWithCard(true)}> Pay With Card </button>
                                            </td>
                                        </tr>
                                    </tbody>



                                </React.Fragment>
                            )
                        })}

                    </table>}


            </div>
        </div>
    )


}

export default ShoppingCart;