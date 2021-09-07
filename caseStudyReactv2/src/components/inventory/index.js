import React, {useEffect, useState} from "react";
import axios from "axios";

const baseURL = "http://localhost:8080/products"

export default function Inventory() {
    const [users, setUsers] = useState([] );

    useEffect(() => {
        axios.get(`${baseURL}`).then((response) => {
            setUsers([response.data] );
        })
    }, [])


    return (
        <div className='inventory'>
            <ul>
                {users.map((items, i) => {
                    return (
                        <ol key={i}>
                            {items.map((subItems, sI) => {
                                return <li key={sI}> {subItems.name} + {subItems.description} + ${subItems.price} </li>
                            })}

                        </ol>
                    )
                })}
            </ul>
        </div>
    );
}