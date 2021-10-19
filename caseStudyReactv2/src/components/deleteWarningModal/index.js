import styles from "./index.module.css"
import axios from "axios";
import Cookies from "js-cookie";

const csrfToken=  Cookies.get('XSRF-TOKEN');

const DeleteWarningModal = (props) =>{

    const goBack = () => {
        props.callBack(false);
    }


    const deleteItem = () => {
        console.log(props.item.id);
        axios({
            method: 'DELETE',
            url: "products",
            params: ({
                id: props.item.id,
            }),
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then((res) => {
            console.log(res);
        }).catch((err) => {
            console.log(err);
        })
        goBack();
    }

    return (
        <div className={styles.checkoutModal}>
            <div className={styles.modalContent}>
                <span>Warning! You are operating with administrator privileges.</span>
                <span>This action cannot be undone</span>
                <span>Do you want to continue?</span>
                <button onClick={() => goBack()}> No </button>
                <button onClick={() => deleteItem()}> Yes </button>
            </div>
        </div>
    )
}

export default DeleteWarningModal;