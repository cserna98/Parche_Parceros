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
