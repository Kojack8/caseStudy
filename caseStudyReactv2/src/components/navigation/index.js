import React from 'react';
import {NavLink} from "react-router-dom";

export default class Navigation extends React.Component {
    render() {
        return (
            <nav>
                <ul>
                    <li><NavLink exact activeClassName="current" to='/'>Home</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/about'>Inventory</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/login'>Login</NavLink></li>
                    <li><NavLink exact activeClassName="current" to='/signup'>Sign-up</NavLink></li>
                </ul>
            </nav>
        );
    }
}