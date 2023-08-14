import React, { useState,useEffect } from "react";
import { StyleSheet, FlatList, Text, View } from "react-native";
import AsistenteCard from "./AsistenteCard";


export default function AsistenteList(props) {


  const {asistentes} = props;

  useEffect(() => {
}, [props]);


  return (
    <View>
      <Text>Los del parche</Text>
      <FlatList
      data={asistentes}
      keyExtractor={(asistente) => String(asistente.id)}
      renderItem={({ item }) => (
        <View style={styles.cardWrapper}>
          <AsistenteCard asistente={item} onDeleteAsistente={props.handleDeleteAsistente}/>
        </View>
      )}
    />
    </View>
    
  );
}

const styles = StyleSheet.create({
  cardWrapper: {
    marginBottom: 10, // Agrega margen inferior entre las tarjetas
  },
});
