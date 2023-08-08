import { API_HOST } from "../utils/Constants";



export async function ParchesApi() {
    try {
        const url = `${API_HOST}/parches`
        const response = await fetch(url);
        const result = await response.json();
        return result;
    } catch (error) {
        throw error;
    }
}

export async function getParcheById(id) {
    try {
        const url = `${API_HOST}/parches/${id}`
        console.log(url)
        const response = await fetch(url);
        const result = await response.json();
        return result;
    } catch (error) {
      throw error;
    }

}
  

export async function postParche(parche){
    try {
        const url = `${API_HOST}/parches`
        const response = await fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(parche),
        });
    
        if (response.ok) {
          const responseData = await response.json();
          console.log('Parche creado:', responseData);
          // Puedes realizar cualquier acción adicional después de crear el parche
        } else {
          console.error('Error al crear el parche:', response.statusText);
          // Puedes manejar el error de acuerdo a tus necesidades
        }
      } catch (error) {
        console.error('Error al crear el parche:', error);
        // Puedes manejar el error de acuerdo a tus necesidades
      }
    };