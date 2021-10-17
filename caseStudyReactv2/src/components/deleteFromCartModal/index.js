import Cookies from "js-cookie";
import React from "react";
import "./deleteFromCartModal.css"
import axios from "axios";

const csrfToken=  Cookies.get('XSRF-TOKEN');

const DeleteFromCartModal = (props) => {

    const removeFromCart = () => {
        axios({
            method: 'DELETE',
            url: `cartitem`,
            params: {
                id: `${props.item.id}`
            },
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((res) => {
            console.log(res);
        }).catch((err) => {
            console.log(err);
        })
    }

    /* Callback method used with parent class */
    const goBack = () => {
        props.callBack(false);
    }

    const exitModal = () => {
        goBack();
    }

    return (
        <div className="modal-div">
            <div className="modal-content">
                <button className="exit-button" onClick={() => exitModal()}> Exit </button>
                <table>
                    <thead>
                        Are you sure you want to delete "{props.item.product}" from your cart?
                    </thead>
                    <tr>
                        <td>
                            <button onClick={() => exitModal()}> No </button>
                        </td>
                        <td>
                            <button onClick={() => removeFromCart()}> Yes </button>
                        </td>
                    </tr>

                </table>

            </div>
        </div>
    )
}

export default DeleteFromCartModal;