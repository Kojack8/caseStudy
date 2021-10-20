import Form from "react-bootstrap/Form";
import React, {useState} from "react";
import Button from "react-bootstrap/Button";
import axios from "axios";
import Cookies from "js-cookie";
import styles from "./index.module.css"

const csrfToken=  Cookies.get('XSRF-TOKEN');
const baseURL = "products"

const InventoryAdmin = () => {
    const [productName, setProductName] = useState("");
    const [productDesc, setProductDesc] = useState("");
    const [productStock, setProductStock] = useState(0);
    const [productPrice, setProductPrice] = useState("0.00");

    const validateForm = () => {
        return productName.length > 0 && productDesc.length > 0 && productStock > 0
        && productPrice > 0;
    }

    const handleSubmit = event => {
        event.preventDefault();
        const product = {
            name: productName, description: productDesc, stock: productStock,
            price: productPrice};
        console.log(product);

        axios({
            method: 'POST',
            url: `${baseURL}`,
            data: product,
            headers: {
                'X-XSRF-TOKEN': csrfToken,

            }
        })
    }


    return(
        <div className={styles.wrapper}>
            <div className={styles.formWrapper}>
                <span> Admin Controls </span>
                <Form onSubmit={handleSubmit}>
                    <Form.Group size="lg" controlId="productName">
                        <Form.Label> Product Name </Form.Label>
                        <Form.Control
                            autoFocus
                            type="text"
                            value={productName}
                            onChange={(e) => setProductName(e.target.value)}
                        />
                    </Form.Group>

                    <Form.Group size="lg" controlId="productDesc">
                        <Form.Label> Product Description </Form.Label>
                        <Form.Control
                            type="text"
                            value={productDesc}
                            onChange={(e) => setProductDesc(e.target.value)}
                        />
                    </Form.Group>

                    <Form.Group size="lg" controlId="productPrice">
                        <Form.Label> Price </Form.Label>
                        <Form.Control
                            type="text"
                            value={productPrice}
                            onChange={(e) => setProductPrice(e.target.value)}

                            pattern="^\d+(?:\.\d{0,2})?$"
                        />
                    </Form.Group>

                    <Form.Group size="lg" controlId="productStock">
                        <Form.Label> Stock </Form.Label>
                        <Form.Control
                            type="number"
                            value={productStock}
                            onChange={(e) => setProductStock(e.target.valueAsNumber)}
                            min={0}
                            max={100}
                            pattern="[0-9]"
                        />
                    </Form.Group>
                    <Button block size="lg" type="submit" disabled={!validateForm()} >
                        Create Inventory Item
                    </Button>
                </Form>
            </div>
        </div>
    )

}

export default InventoryAdmin;