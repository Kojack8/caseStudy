import React, {useEffect, useState} from "react"
import axios from 'axios';
import Cookies from "js-cookie";


const baseURL = "cart"
const csrfToken=  Cookies.get('XSRF-TOKEN');


function ShoppingCart(){

    const [cartItems, setCartItems] = useState([] );

    useEffect(() => {
        axios(`${baseURL}`, {
                method: 'GET',
            headers: {

                'X-XSRF-TOKEN': csrfToken
            }
            }
        ).then((response) => {
            setCartItems([response.data] )

        })
    }, [])




    return (
        <div>
            Your Shopping Cart:
            { cartItems.length !== 0 ?
                <ul>
                    {cartItems.map((items, i) => {
                        return (
                            <ol key={i}>
                                {items.map((subItems, sI) => {
                                    return <li key={sI}> {subItems.product} + {subItems.quantity} </li>
                                })}

                            </ol>
                        )
                    })}
                </ul>
                : "Your cart is empty"}
            {/* {users.hits.map(user => <div> {user[1].name}</div>)} */}

        </div>
    )


}

export default ShoppingCart;