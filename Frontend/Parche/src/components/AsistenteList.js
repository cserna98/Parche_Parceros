import React, { useState,useEffect } from "react";
import { StyleSheet, FlatList, Text, View, ScrollView } from "react-native";
import AsistenteCard from "./AsistenteCard";


export default function AsistenteList(props) {


  const {asistentes} = props;

  useEffect(() => {
    console.log(asistentes)
}, [props]);


  return (
    <View>
      <Text>Los del parche</Text>
      <ScrollView style={styles.asistenteList}>
      <FlatList
      data={asistentes}
      keyExtractor={(asistente) => String(asistente.email)}
      renderItem={({ item }) => (
        <View style={styles.cardWrapper}>
          <AsistenteCard asistente={item} onDeleteAsistente={props.handleDeleteAsistente}/>
        </View>
      )}
    />
    </ScrollView>
    </View>
    
  );
}

const styles = StyleSheet.create({
  cardWrapper: {
    marginBottom: 5, // Agrega margen inferior entre las tarjetas
  },
});
