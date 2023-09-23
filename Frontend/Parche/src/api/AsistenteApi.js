import { API_HOST } from "../utils/Constants";

export async function getAsistentesByParcheId(parcheId, token) {
  try {
    const url = `${API_HOST}/asistentes/${parcheId}/asistentes`;
    const response = await fetch(url,{
      headers: {
        'Authorization': `Bearer ${token}`, // Agrega el token de autorización en el encabezado
        'Content-Type': 'application/json'
    }
    });
    const result = await response.json();
    return result;
  } catch (error) {
    console.log("error" + error )
    throw error;
  }
}

export async function deleteAsistenteAPI(id) {
  try {
    const url = `${API_HOST}/asistentes/${id}`;
    const response = await fetch(url, {
      method: 'DELETE',
    });
    console.log(response.status)

    if (response.ok) {
      // El asistente se eliminó exitosamente
      return true;
    } else {
      // Manejo de errores si la eliminación falló
      const errorData = await response.json();
      console.error('Error al eliminar asistente:', errorData);
      return false;
    }
  } catch (error) {
    console.error('Error al realizar la petición DELETE:', error);
    throw error;
  }
}


export async function postAsistente(asistente, idParche,token) {
  try {
    console.log("ingrese a try ")
    const url = `${API_HOST}/asistentes/${idParche}/parche`;
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(asistente),
    });
    console.log(response)
    return response
    if (response.ok) {
      const responseData = await response.json();
      console.log('Asistente creado:', responseData);
      // Puedes realizar cualquier acción adicional después de crear el asistente
    } else {
      console.error('Error al crear el asistente:', response.statusText);
      // Puedes manejar el error de acuerdo a tus necesidades
    }
  } catch (error) {
    console.error('Error al crear el asistente:', error);
    // Puedes manejar el error de acuerdo a tus necesidades
  }
}
