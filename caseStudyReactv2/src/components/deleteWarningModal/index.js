import "./deleteWarningModal.css"

const DeleteWarningModal = () =>{

    return (
        <div className="checkout-modal">
            <div className="modal-content">
                <span>Warning! You are operating with administrator privileges.</span>
                <span>This action cannot be undone</span>
                <span>Do you want to continue?</span>
            </div>
        </div>
    )
}

export default DeleteWarningModal;