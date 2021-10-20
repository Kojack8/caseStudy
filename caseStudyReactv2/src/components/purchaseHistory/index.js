import React, {useEffect, useState} from "react";
import axios from "axios";
import Cookies from "js-cookie";
import styles from "./index.module.css"

const csrfToken=  Cookies.get('XSRF-TOKEN');

const PurchaseHistory = () => {

    const [orderHistory, setOrderHistory] = useState([]);

    useEffect(() => {
        axios("purchase", {
                method: 'GET',
                headers: {

                    'X-XSRF-TOKEN': csrfToken
                }
            }
        ).then((response) => {
            setOrderHistory([response.data] )

        })
    }, [])

    return(
        <div className={styles.wrapper}>
            {orderHistory.length !==0 ?
                <div className={styles.tableWrapper}>
                    <table>
                        <thead>
                        <tr>
                            <td>Product</td>
                            <td>Quantity</td>
                            <td>Purchased At</td>
                        </tr>
                        </thead>

                        {orderHistory.map((items, i) => {
                            return (
                                <React.Fragment key = {i}>
                                    <tbody>
                                    {items.map((subItems, sI) => {
                                        //return <li key={sI}> {subItems.product} + {subItems.quantity} </li>
                                        return(
                                            <tr key={sI}>
                                                <td>{subItems.productName}</td>
                                                <td>{subItems.quantity}</td>
                                                <td>{subItems.purchasedAt}</td>

                                            </tr>
                                        )
                                    })}
                                    </tbody>
                                </React.Fragment>
                            )
                        })}
                    </table>
                </div>


            : null}
        </div>
    )

}

export default PurchaseHistory;