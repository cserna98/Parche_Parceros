import React from "react";
import { View, Text, StyleSheet } from "react-native";
import { capitalize } from "lodash";
import StatusBarMargin from "../components/StatusBarMargin";
import AttributeRow from "../components/AttributeRow";


export default function AsistenteInfo({ route }) {
  const { asistente } = route.params;

  return (
    <StatusBarMargin>
   <View style={[styles.containerAtributerow, { flex: 3 }]}>
          {/* Renderiza las filas de atributos */}
          {AttributeRow('Nombre', asistente?.nombre)}
          {AttributeRow('Fecha Inicio',)}
          {AttributeRow('Fecha Fin', ``)}
          {AttributeRow('Costo', "")}
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
