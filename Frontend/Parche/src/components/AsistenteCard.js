import React, {useEffect} from "react";
import { StyleSheet, View, Text, TouchableWithoutFeedback } from "react-native";
import { Menu, IconButton } from "react-native-paper";
import { capitalize } from "lodash";
import { useNavigation } from "@react-navigation/native";
import { deleteAsistenteAPI } from "../api/AsistenteApi";

export default function AsistenteCard(props) {
    
  const [visible, setVisible] = React.useState(false);
  const { asistente } = props;
  const navigation = useNavigation();

  const openMenu = () => setVisible(true);
  const closeMenu = () => setVisible(false);

  const goToAsistente = () => {
    navigation.navigate("asistenteInfo", { asistente: asistente });
  };


  const deleteAsistente= async () => {
    try {
        console.log("soy id"+ asistente.id)
        await deleteAsistenteAPI(asistente.id)
        console.log(props)
        props.onDeleteAsistente();
        // Aquí puedes realizar alguna acción adicional después de eliminar el item
    } catch (error) {
      console.error("Error al eliminar asistente", error);
}
};

  useEffect(() => {
    console.log("Asistente" + asistente);
  }, [props]);

  return (
    <TouchableWithoutFeedback onPress={goToAsistente}>
      <View style={styles.card}>
        <View style={styles.bgStyles}>
          <View style={styles.textContainer}>
            <Text style={styles.name}>{capitalize(asistente.nombre)}</Text>
            <Text style={styles.dias}>Días: {asistente.dias}</Text>
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
              <Menu.Item onPress={deleteAsistente} title="Borrar" />
            </Menu>
          </View>
        </View>
      </View>
    </TouchableWithoutFeedback>
  );
}

const styles = StyleSheet.create({
  card: {
    flex: 2,
    height: "auto", // Establece la altura automática
  },
  bgStyles: {
    borderRadius: 10,
    padding: 10,
    backgroundColor: "grey",
    flexDirection: "row", // Alinea los elementos en fila
    justifyContent: "space-between", // Espacio entre los elementos
    alignItems: "center", // Centra verticalmente los elementos
  },
  textContainer: {
    flex: 1, // Ocupa el espacio restante en la caja
  },
  name: {
    color: "#fff",
    fontWeight: "bold",
    fontSize: 15,
  },
  dias: {
    color: "#fff",
    fontSize: 13,
  },
  menuContainer: {
    marginLeft: 10, // Agrega un margen entre el texto y los tres puntos
  },
});