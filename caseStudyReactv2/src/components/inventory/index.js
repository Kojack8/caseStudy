import React, {useEffect, useState} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import {Form} from "react-bootstrap";
import Cookies from "js-cookie";
import "./inventory.css"
import AddToCartModal from "../addToCartModal";


const csrfToken=  Cookies.get('XSRF-TOKEN');
const baseURL = "search"

const Inventory = () => {

    const [searchName, setSearchName] = useState("");
    const [products, setProducts] = useState("");
    const [selectedProduct, setSelectedProduct] = useState([]);
    const [showModal, setShowModal] = useState(false);

    const modalCallBackData = (data) => {
        setShowModal(data);
    }


    const handleSubmit = (event) => {
        event.preventDefault();
        axios({
            method: 'GET',
            url: `${baseURL}`,
            params: {
                name: searchName
            },
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((res) => {
            console.log(res);
            setProducts(res.data)
        }).catch((err) => {
            console.log(err);
        })
    }

    const addToCart = (items) => {
        setShowModal(true);
        setSelectedProduct(items)
        /*axios({

            method: 'POST',
            url: "cartitem",
            data: ({
                id
            }),
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((res) => {
            console.log(res);
        }).catch((err) => {
            console.log(err);
        })*/
    }

    return (

        <div className="inv-page">
            {showModal ? <AddToCartModal  item={selectedProduct} callBack={modalCallBackData}/> : null}

            <h2>Hey it's me the inventory page</h2>
            <Form className="Search" onSubmit={handleSubmit}>
                <input type="text"
                       placeholder="Search"
                       value={searchName}
                       onChange={(e) => setSearchName(e.target.value)}
                />
                <Button block size="sm" type="submit">
                    Search
                </Button>
            </Form>
            <br/>


            { products !== "" ?
                <table>
                    <thead>
                    <tr>
                        <td>Name</td>
                        <td>Description</td>
                        <td>Stock</td>
                        <td>Price</td>
                        <td>Last Update</td>
                        <td>Add to cart</td>
                    </tr>
                    </thead>
                <tbody>
                    {products.map((items, i) => {
                        return (
                            <tr key={i}>
                                <td>{items.name}</td>
                                <td>{items.description}</td>
                                <td>{items.stock}</td>
                                <td>{items.price}</td>
                                <td>{items.updatedDate}</td>
                                <td>
                                    <button onClick={() => addToCart(items)}> Add </button>
                                </td>
                            </tr>
                        )
                    })
                    }
                    </tbody>
                </table>
            : null }
        </div>
    )
}

export default Inventory;