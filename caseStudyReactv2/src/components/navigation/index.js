import React from 'react';
import {NavLink} from "react-router-dom";

const Navigation = ({username}) => {


    if (username === "") {
        return (
            <nav>
                <ul>
                    <li><NavLink exact activeClassName="current" to='/'>Home</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/inventory'>Inventory</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/login'>Login</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/signup'>Sign-up</NavLink></li>

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
                <li><NavLink exact activeClassName="current" to='/logout'>Logout</NavLink></li>
            </ul>
        </nav>
    );


}

export default Navigation;