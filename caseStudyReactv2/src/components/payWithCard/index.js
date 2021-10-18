import {Form} from "react-bootstrap";
import React from "react";

const PayWithCard = (props) => {

    const checkOut = () => {
        goBack();
    }

    const goBack = () => {
        props.callBack();
    }



    return(
        <div className="checkout-modal">
            <div className="modal-content">
                This page is for demonstration purposes only. Please, do not enter your
                credit card information. Simply press "Submit Payment" to continue.
                <button className="exit-button" onClick={() =>goBack()}> Exit </button>
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
                            maxlength="3"
                        />
                    </Form.Group>
                </Form>
                <button onClick={() => checkOut()}> Submit Payment </button>
            </div>
        </div>
    )


}
export default PayWithCard;