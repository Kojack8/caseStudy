import React, {useState} from "react";


export default class CountEXAMPLE extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            count: 0
        };

    }

    render()
    {
        return (
            <div>
                <p> You clicked {this.state.count} times</p>
                <button onClick={() => this.setState({count: this.state.count + 1})}>
                    Push me
                </button>
            </div>
        );
    }
};