import { API_HOST } from "../utils/Constants";

export async function getItems() {
  try {
    const url = `${API_HOST}/items`;
    const response = await fetch(url);
    const result = await response.json();
    return result;
  } catch (error) {
    throw error;
  }
}

export async function getItemById(id) {
  try {
    const url = `${API_HOST}/items/${id}`;
    console.log(url);
    const response = await fetch(url);
    const result = await response.json();
    return result;
  } catch (error) {
    throw error;
  }
}

export async function deleteItemAPI(itemId) {
  try {
    console.log("por ac asi entre")
    const url = `${API_HOST}/items/${itemId}`;
    const response = await fetch(url, {
      method: "DELETE",
    });

    if (response.ok) {
      console.log(response);
    } else {
      throw new Error("Error al eliminar el item");
    }
  } catch (error) {
    throw error;
  }
}

export async function getItemsByParcheId(parcheId) {
  try {
    const url = `${API_HOST}/items/${parcheId}/items`;
    console.log(url)
    const response = await fetch(url);
    const result = await response.json();
    return result;
  } catch (error) {
    throw error;
  }
}


export async function postItem(item) {

  try {
    const url = `${API_HOST}/items`;
    console.log(url)
    const response = await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(item),
    });
    console.log(response)
    if (response.ok) {
      const responseData = await response.json();
      console.log('Item creado:', responseData);
      // Puedes realizar cualquier acción adicional después de crear el item
    } else {
      console.error('Error al crear el item  :', response.statusText);
      // Puedes manejar el error de acuerdo a tus necesidades
    }
  } catch (error) {
    console.error('Error al crear el item  :', error);
    // Puedes manejar el error de acuerdo a tus necesidades
  }

}
