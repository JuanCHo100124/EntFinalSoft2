import { useState } from "react";
import {getAuth, signInWithEmailAndPassword} from "firebase/auth";
import { firebaseApp } from "../../firebaseConfig";

function Login (){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleLogin = async ()=>{
        try {
            const auth = getAuth(firebaseApp)
            await signInWithEmailAndPassword(auth, email, password)
            console.log("User authenticated")
            setErrorMessage("El usuario ha ingresado")

        } catch (error) {
            console.log('Error log in user',error.message) 
            setErrorMessage(errorMessage)           

        }
    }

    return (
        <div className="Login">
            <h2>Inicio de sesion</h2>
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />   
            <input type="password" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} />

            <button onClick={handleLogin}>Ingresar</button>
            { errorMessage && <p>{errorMessage}</p>}
        </div>
    )
}
export default Login