import Cookies from "js-cookie";
import React from "react";
import styles from "./index.module.css"
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
            goBack();
            window.location.reload(false);
        }).catch((err) => {
            console.log(err);
        })
    }

    /* Callback method used with parent class */
    const goBack = () => {
        props.callBack();
    }

    const exitModal = () => {
        goBack();

    }

    return (
        <div className={styles.modalDiv}>
            <div className={styles.modalContent}>
                <button className={styles.exitButton} onClick={() => exitModal()}> Exit </button>
                <table>
                    <thead>
                        <tr>
                            <td>
                                Are you sure you want to delete "{props.item.productName}" from your cart?
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <button onClick={() => exitModal()}> No </button>
                            </td>
                            <td>
                                <button onClick={() => removeFromCart()}> Yes </button>
                            </td>
                        </tr>
                    </tbody>

                </table>

            </div>
        </div>
    )
}

export default DeleteFromCartModal;