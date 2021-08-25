import {Route, Switch} from "react-router-dom";
import React from "react";
import Home from "../home/index.js";
import About from "../about/index.js";
import Contact from "../contact/index.js"

export default class Router extends React.Component {
    render() {
        return (
            <Switch>
                <Route exact path='/' component={Home}/>
                <Route exact path='/about' component={About}/>
                <Route exact path='/contact' component={Contact}/>
            </Switch>
        );
    }
}