import React, {useEffect, useState} from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import {Form} from "react-bootstrap";
import Cookies from "js-cookie";
import "./inventory.css"


const csrfToken=  Cookies.get('XSRF-TOKEN');
const baseURL = "search"

const Inventory = () => {

    const [searchName, setSearchName] = useState("");
    const [products, setProducts] = useState("");

    useEffect(() => {
        //This logs the new XSRF-TOKEN after a user logs out and is not related to the search function
        axios({ url: `${baseURL}`,
                method: 'GET',
            }
        ).then()
        .catch()
    }, [])

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

    return (
        <div className="InvPage">
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

            <table>
                <thead>
                <tr>
                    <td>Name</td>
                    <td>Description</td>
                    <td>Stock</td>
                    <td>Price</td>
                    <td>Last Update</td>
                </tr>
                </thead>
            { products !== "" ?
                <tbody>
                {products.map((items, i) => {
                    return (
                        <tr key={i}>
                            <td>{items.name}</td>
                            <td>{items.description}</td>
                            <td>{items.stockAmount}</td>
                            <td>{items.price}</td>
                            <td>{items.updatedDate}</td>
                        </tr>
                    )
                })
                }
                </tbody>
            : null }
            </table>
        </div>
    )
}

export default Inventory;