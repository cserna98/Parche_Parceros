import React, { useState, useEffect } from "react";
import { StyleSheet, FlatList, StatusBar } from "react-native";
import ItemCard from "../components/ItemCard"; // Asegúrate de importar el componente correcto para los items
import { getItemsByParcheId } from '../api/ItemApi'; // Importa la función de la API para obtener los items
import { useNavigation } from '@react-navigation/native';
import StatusBarMargin from "../components/StatusBarMargin";


    export default function ItemList(props) {

    const [items, setItems] = useState([]);

    const {
        navigation,
        route: {params},
    } = props;

    const navigate = useNavigation();

  

    const getItems = async (id) => {
        try {
            const response = await getItemsByParcheId(id); // Llama a la API para obtener los items por parcheId
            setItems(response);
            console.log(response)
        } catch (error) {
            console.log("Error al obtener los items:", error);
            // Puedes manejar el error de acuerdo a tus necesidades
        }
    };

    const handleDeleteItem = () => {
        getItems(params.idParche); // Llama nuevamente a getItems para actualizar la lista
    };

    useEffect(() => {
        console.log(" render ")
        getItems(params.idParche);
    }, [params]);


    return (
        <StatusBarMargin>
            <FlatList
            style={styles.container}
            data={items}
            keyExtractor={(item) => String(item.id)}
            renderItem={({ item }) => <ItemCard item={item} onDeleteItem={handleDeleteItem} />}
            />
        </StatusBarMargin>
    );
    }

    const styles = StyleSheet.create({
        container: {
        flex: 1,
        marginTop: Platform.OS === 'android' ? StatusBar.currentHeight : 0,
        }
    });
    