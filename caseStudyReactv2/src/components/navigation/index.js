import React, {useState} from 'react';
import {NavLink} from "react-router-dom";
import styles from "./index.module.css";
import Button from "react-bootstrap/Button";
import {Form} from "react-bootstrap";

const Navigation = (props) => {


    if (props.username === "") {
        return (
            <nav>
                <ul>
                    <li><NavLink exact activeClassName="current" to='/'>Home</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/inventory'>Inventory</NavLink></li>
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
                <li><NavLink exact activeClassName="current" to='/purchase_history'>Purchase History</NavLink></li>
                <li><NavLink exact activeClassName="current" to='/logout'>Logout</NavLink></li>
            </ul>
        </nav>
    );


}

export default Navigation;