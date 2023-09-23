import { API_HOST } from "../utils/Constants";



export async function getParches(token) {
    try {
      console.log(token)
        const url = `${API_HOST}/parches`
        const response = await fetch(url, {
          headers: {
            'Authorization': `Bearer ${token}`, // Agrega el token de autorización en el encabezado
            'Content-Type': 'application/json'
        }
        });
        console.log(response)
        const result = await response.json();
        return result;
    } catch (error) {
        throw error;
    }
}

export async function getParcheById(id, token) {
    try {
       
        const url = `${API_HOST}/parches/${id}`
        console.log(url)
        const response = await fetch(url,{
          headers: {
            'Authorization': `Bearer ${token}`, // Agrega el token de autorización en el encabezado
            'Content-Type': 'application/json'
        }
        });
        const result = await response.json();
        return result;
    } catch (error) {
      throw error;
    }

}
  

export async function postParche(parche, token){
    try {
        const url = `${API_HOST}/parches`
        const response = await fetch(url, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`,
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

    export async function deleteParche(id) {
      try {
          const url = `${API_HOST}/parches/${id}`;
          const response = await fetch(url, {
              method: 'DELETE',
              headers: {
                  'Content-Type': 'application/json',
              },
          });
  
          if (response.ok) {
              console.log('Parche eliminado con éxito');
              // Puedes realizar cualquier acción adicional después de eliminar el parche
          } else {
              console.error('Error al eliminar el parche:', response.statusText);
              // Puedes manejar el error de acuerdo a tus necesidades
          }
      } catch (error) {
          console.error('Error al eliminar el parche:', error);
          // Puedes manejar el error de acuerdo a tus necesidades
      }
  }
  