import React from 'react';
import {NavLink} from "react-router-dom";
import styles from "./index.module.css";

const Navigation = (props) => {


    if (props.username === "") {
        return (
            <nav>
                <ul>
                    <li><NavLink exact activeClassName="current" to='/'>Home</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/inventory'>Inventory</NavLink></li>
                    <li className={styles.title}><h1>Video Production Shopping Service</h1></li>
                    <li className={styles.login}><NavLink exact activeClassName="current" to='/login'>Login</NavLink></li>
                    <li className={styles.signUp}><NavLink exact activeClassName="current" to='/signup'>Sign-up</NavLink></li>

                </ul>
            </nav>
        );
    }
    return (

        <nav>
            <ul>
                <li><NavLink exact activeClassName="current" to='/'>Home</NavLink></li>
                <li><NavLink exact activeClassName="current" to='/inventory'>Inventory</NavLink></li>
                <li><NavLink exact activeClassName="current" to='/cart'>Shopping Cart</NavLink></li>
                <li className={styles.title}><h1>Video Production Shopping Service</h1></li>
                <li>eeeeee</li>
                <li><NavLink exact activeClassName="current" to='/purchase_history'>Purchase History</NavLink></li>
                <li><NavLink exact activeClassName="current" to='/logout'>Logout</NavLink></li>
            </ul>
        </nav>
    );


}

export default Navigation;