import "./addToCartModal.css"
import React, {useEffect, useState} from "react";
import Cookies from "js-cookie";
import axios from "axios";

const csrfToken=  Cookies.get('XSRF-TOKEN');

const AddToCartModal = (props) => {

    const [orderQuantity, setOrderQuantity] = useState(1);



    /* Callback method used with parent class */
    const goBack = () => {
        props.callBack(false);
    }

    const addToCart = () => {
        if (orderQuantity > 0 && orderQuantity <= props.item.stock) {
            axios({

                method: 'POST',
                url: "cartitem",
                params: ({
                    id: `${props.item.id}`,
                    quantity: `${orderQuantity}`
                }),
                headers: {
                    'X-XSRF-TOKEN': csrfToken
                }
            }).then((res) => {
                console.log(res);
            }).catch((err) => {
                console.log(err);
            })
            exitModal();
        }
    }

    const exitModal = () => {
        setOrderQuantity(0);
        goBack();
    }

    return (
        <div className="checkout-modal">
            <div className="modal-content">
                <button className="exit-button" onClick={() => exitModal()}> Exit </button>
                <table>
                    <thead>Add To Cart:</thead>
                    <tr>
                        <td>{props.item.name}</td>
                        <td>{props.item.description}</td>
                        <td>
                            <label>
                                Quantity:
                            </label>
                            <input type="number"
                                value={orderQuantity}
                                onChange={(e) => setOrderQuantity(e.target.value)}
                                min={0}
                                max={props.item.stock}
                            />
                        </td>
                        <td>
                            <button onClick={() => addToCart()}> Add </button>
                        </td>
                    </tr>
                </table>{ props.item.stock === 0 ?
                <span class="no-stock"> Sorry, this item is out of stock </span>
                : null}



            </div>
        </div>
    )
}

export default AddToCartModal;