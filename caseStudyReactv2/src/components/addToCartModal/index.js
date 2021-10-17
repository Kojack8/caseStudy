import "./addToCartModal.css"

const AddToCartModal = (props) => {


    return (
        <div className="checkout-modal">
            <div className="modal-content">
                <table>
                    <tr>
                        <td>yooo</td>
                        <td>{props.item.name}</td>
                    </tr>

                </table>
            </div>
        </div>
    )
}

export default AddToCartModal;