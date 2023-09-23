import { API_HOST } from "../utils/Constants";

export async function AuthLogin(credentials){
    try {
        const url = `${API_HOST}/auth/login`
        const response = await fetch(url, {
            method:'POST',
            headers :{

                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        });
        console.log(response.headers)
        return response

    }catch(error){
        console.error('Error al iniciar seccion:', error);
    }

}

export async function AuthRegister(userData) {
    try {
        const url = `${API_HOST}/usuarios`; // Cambiar la URL según la ruta de registro de tu API
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });
        console.log(response.headers);
        return response;
    } catch (error) {
        console.error('Error al registrar usuario:', error);
    }
}
