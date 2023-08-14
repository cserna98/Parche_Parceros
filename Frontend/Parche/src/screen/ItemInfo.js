import React from "react";
import { View, Text, StyleSheet } from "react-native";
import { capitalize } from "lodash";
import StatusBarMargin from "../components/StatusBarMargin";

export default function ItemInfo({ route }) {
  const { item } = route.params;

  return (
    <StatusBarMargin>
        <View style={styles.container}>
      <View style={styles.row}>
        <Text style={styles.attribute}>ID:</Text>
        <Text style={styles.value}>{item.id}</Text>
      </View>
      <View style={styles.row}>
        <Text style={styles.attribute}>Nombre:</Text>
        <Text style={styles.value}>{capitalize(item.nombre)}</Text>
      </View>
      <View style={styles.row}>
        <Text style={styles.attribute}>Costo:</Text>
        <Text style={styles.value}>${item.costo}</Text>
      </View>
      {/* Agrega más filas aquí para otros atributos */}
    </View>
    </StatusBarMargin>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    padding: 20,
  },
  row: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 10,
  },
  attribute: {
    fontWeight: "bold",
    fontSize: 18,
  },
  value: {
    fontSize: 18,
  },
});
