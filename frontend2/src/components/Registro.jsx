import { useState } from "react";
import {getAuth, createUserWithEmailAndPassword} from "firebase/auth";
import { firebaseApp } from "../../firebaseConfig";

function Registro (){

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleRegistro = async ()=>{
        try {
            const auth = getAuth(firebaseApp)
            await createUserWithEmailAndPassword(auth, email, password)
            console.log("User created")
            setErrorMessage("El usuaria fue creado con exito")

        } catch (error) {
            console.log('Error creating user',error.message) 
            setErrorMessage(errorMessage)           

        }
    }

    return (
        <div className="Registro">
            <h2>Registro</h2>
            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />   
            <input type="password" placeholder="password" value={password} onChange={(e) => setPassword(e.target.value)} /> 
            <button onClick={handleRegistro}>Registrarme</button>
            { errorMessage && <p>{errorMessage}</p>}
        </div>
    )
}
export default Registro