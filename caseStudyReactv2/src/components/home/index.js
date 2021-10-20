import React from "react";
import styles from "./index.module.css"
import dev from './../../assets/illustrations/undraw_videographer_nnc7.svg';

export default function Home(){

    return (
        <div className={styles.wrapper}>

            <h1>This is a simple welcome page</h1>
            <p> This page will be fairly low priority for the time being.</p>
            <img src={dev} alt="Man leaning on film camera"/>
        </div>
    );

}