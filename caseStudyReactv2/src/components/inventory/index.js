import React, {useEffect, useState} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import {Form} from "react-bootstrap";
import Cookies from "js-cookie";
import "./inventory.css"
import AddToCartModal from "../addToCartModal";
import InventoryAdmin from "../inventoryAdmin";
import DeleteWarningModal from "../deleteWarningModal";


const csrfToken=  Cookies.get('XSRF-TOKEN');
const baseURL = "search"

const Inventory = (props) => {

    const [searchName, setSearchName] = useState("");
    const [products, setProducts] = useState("");
    const [selectedProduct, setSelectedProduct] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [showWarning, setShowWarning] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        if (props.auth.length !== 0 ) {
            props.auth.forEach(element => {
                if (element.authority === "ROLE_ADMIN") {
                    setIsAdmin(true);
                }
            })
        }
    })

    const modalCallBackData = (data) => {
        setShowModal(data);
    }

    const warningCallBackData = (data) => {
        setShowWarning(data);
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

    const deleteFromDataBase = (items) => {
        setShowWarning(true);
        setSelectedProduct(items);
    }

    const addToCart = (items) => {
        setShowModal(true);
        setSelectedProduct(items);
    }

    return (

        <div className="inv-page">

            {showWarning ? <DeleteWarningModal item={selectedProduct} callBack={warningCallBackData}/> : null}
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
                        <td>Add to cart</td>
                        { isAdmin ? <td> Delete From Database</td> : null}
                    </tr>
                    </thead>
                <tbody>
                    {products.map((items, i) => {
                        return (
                            <tr key={i}>
                                <td>{items.name}</td>
                                <td>{items.description}</td>
                                { items.stock !==0 ? <td>{items.stock}</td> : <td className="no-stock"> Out Of Stock</td>}
                                <td>{items.price}</td>
                                <td>
                                    <button onClick={() => addToCart(items)}> Add </button>
                                </td>
                                { isAdmin ? <td>
                                    <button onClick={() => deleteFromDataBase(items)}> Delete</button>
                                </td> : null}
                            </tr>
                        )
                    })
                    }
                    </tbody>
                </table>
            : null }

            {isAdmin ? <InventoryAdmin/> : null}
        </div>
    )
}

export default Inventory;