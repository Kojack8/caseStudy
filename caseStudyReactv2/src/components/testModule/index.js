import React, {useEffect, useState} from "react"
import axios from 'axios';


const baseURL = "http://localhost:8080/users"

export default function TestModule(){

    const [users, setUsers] = useState([] );

    useEffect(() => {
        axios.get(`${baseURL}`).then((response) => {
            setUsers([response.data] );
        })
    }, [])




    return (
       <div>
           <ul>
               {users.map((items, i) => {
                   return (
                       <ol key={i}>
                           {items.map((subItems, sI) => {
                           return <li key={sI}> {subItems.fullName} + {subItems.email} </li>
                           })}

                       </ol>
                   )
               })}
           </ul>
           {/* {users.hits.map(user => <div> {user[1].name}</div>)} */}

       </div>
    )


}