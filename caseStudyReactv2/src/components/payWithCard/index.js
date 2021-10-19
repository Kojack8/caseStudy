import {Form} from "react-bootstrap";
import React from "react";
import {useHistory} from "react-router-dom";
import axios from "axios";
import Cookies from "js-cookie";
import styles from "./index.module.css"

const csrfToken=  Cookies.get('XSRF-TOKEN');

const PayWithCard = (props) => {

    const history = useHistory();

    const checkOut = () => {
        axios(`purchase`, {
                method: 'POST',

                headers: {
                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => {
            goBack();
            history.push("/purchase_history");
            window.location.reload(false);
        })

    }

    const goBack = () => {
        props.callBack();
    }



    return(
        <div className={styles.checkoutModal}>
            <div className={styles.modalContent}>
                This page is for demonstration purposes only. Please, do not enter your
                credit card information. Simply press "Submit Payment" to continue.
                <button className={styles.exitButton} onClick={() =>goBack()}> Exit </button>
                <Form>
                    <Form.Group controlId="name">
                        <Form.Label>Name On Card</Form.Label>
                        <Form.Control
                            type="text"
                        />
                    </Form.Group>

                    <Form.Group controlId="card_number">
                        <Form.Label>Credit Card Number</Form.Label>
                        <Form.Control
                            autoFocus
                            type="text"
                            pattern="[0-9]*"
                        />
                    </Form.Group>

                    <Form.Group controlId="expiration">
                        <Form.Label>Expiration Date</Form.Label>
                        <Form.Control
                            autoFocus
                            type="text"
                            pattern="[0-9/]*"
                        />
                    </Form.Group>

                    <Form.Group controlId="card_sec">
                        <Form.Label>Card Security Code</Form.Label>
                        <Form.Control
                            autoFocus
                            type="text"
                            pattern="[0-9/]*"
                            maxLength="3"
                        />
                    </Form.Group>
                </Form>
                <button onClick={() => checkOut()}> Submit Payment </button>
            </div>
        </div>
    )


}
export default PayWithCard;