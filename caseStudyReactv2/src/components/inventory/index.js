import React, {useEffect, useState} from "react";
import axios from "axios";

const baseURL = "products"

export default function Inventory() {
    const [users, setUsers] = useState([] );

    useEffect(() => {
        axios(`${baseURL}`, {
                method: 'GET',
                headers: {
                    Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb')
                }
            }
        ).then((response) => {
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