import React from "react";
import styles from "./index.module.css"

export default function Home(){

    return (
        <div className={styles.wrapper}>

            <h1>This is a simple welcome page</h1>
            <p> This page will be fairly low priority for the time being.</p>
        </div>
    );

}