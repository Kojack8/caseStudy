import styles from "./index.module.css"
import dev from "./../../assets/illustrations/undraw_page_not_found_su7k.svg";
import dev2 from "./../../assets/illustrations/undraw_not_found_60pq.svg";


const NotFound = () => {

    return (
        <div className={styles.wrapper}>
            <span className={styles.title}>
                Sorry, this page doesn't exist.
            </span>
            <div className={styles.images}>
                <div className={styles.leftImgWrapper}>
                    <img className={styles.left} src={dev} alt="Woman sitting atop 404 text"/>
                </div>
                <div className={styles.rightImgWrapper}>
                    <img className={styles.right} src={dev2} alt="Aliens hiding behind bushes (not found)"/>
                </div>
            </div>
        </div>
    )

}

export default NotFound;