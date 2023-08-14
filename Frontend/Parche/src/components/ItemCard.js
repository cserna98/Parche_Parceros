import React, {useEffect} from "react";
import { StyleSheet, View, Text, Image, TouchableWithoutFeedback } from "react-native";
import { capitalize } from "lodash";
import { useNavigation } from "@react-navigation/native";
import { Menu, IconButton } from "react-native-paper";
import { deleteItemAPI } from "../api/ItemApi";

export default function ItemCard(props) {
    const { item } = props;

    const navigate = useNavigation();

    const goToItemInfo= () => {
        navigate.navigate('Item Info',{ item: item });
      };

    const [visible, setVisible] = React.useState(false);
    const [id, setId] = React.useState()

    const openMenu = () => setVisible(true);
    const closeMenu = () => setVisible(false);

    const deleteItem = async () => {
        try {
            console.log("soy id"+id)
            await deleteItemAPI(id)
            props.onDeleteItem();
            // Aquí puedes realizar alguna acción adicional después de eliminar el item
        } catch (error) {
        console.error("Error al eliminar el item:", error);
    }
    };
    useEffect(() => {
        setId(item.id)
    },[props]);


    return (
    <TouchableWithoutFeedback onPress={goToItemInfo}>
        <View style={styles.card}>
            <View style={styles.spacing}>
                <View style={styles.bgStyles}>
                    <Text style={styles.number}>#{item.id}</Text>
                    <Text style={styles.name}>{capitalize(item.nombre)}</Text>
                    <Text style={styles.date}>Costo: ${item.costo}</Text>
                </View>
            </View>
            <View style={styles.menuContainer}>
                <Menu
                visible={visible}
                onDismiss={closeMenu}
                anchor={
                    <IconButton
                    icon="dots-vertical"
                    color="white"
                    size={20}
                    onPress={openMenu}
                />
            }
            >
                    <Menu.Item onPress={deleteItem} title="Borrar" />
                </Menu>
            </View>
        </View>
    </TouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  card: {
    flex: 2,
    height: 130,
    flexDirection: "row", // Para alinear el menú a la derecha
  },
  spacing: {
    flex: 1,
    padding: 5,
  },
  bgStyles: {
    flex: 1,
    borderRadius: 10,
    padding: 10,
    backgroundColor: "grey",
  },
  number: {
    position: "absolute",
    right: 5,
    top: 5,
    color: "#fff",
    fontSize: 11,
  },
  name: {
    color: "#fff",
    fontWeight: "bold",
    fontSize: 15,
    top: 0,
    left: 5,
  },
  date: {
    color: "#fff",
    fontSize: 13,
  },
  menuContainer: {
    position: "absolute",
    top: 5,
    right: 5,
  },
});
