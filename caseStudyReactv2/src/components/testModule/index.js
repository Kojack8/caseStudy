import React, {useEffect, useState} from "react"
import axios from 'axios';


const baseURL = "users"


export default function TestModule(){

    const [users, setUsers] = useState([] );

    useEffect(() => {
        axios(`${baseURL}`, {
            method: 'GET',
                headers: {
                    Authorization: 'Basic ' + window.btoa('caseStudyUser:Hamster5Lobster9Lightbulb')
                }
            }
            ).then((response) => {
            setUsers([response.data] )

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