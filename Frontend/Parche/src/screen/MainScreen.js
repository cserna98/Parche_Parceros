import React, { useState } from 'react';
import { View, StyleSheet, Dimensions, StatusBar,Text   } from 'react-native';
import { Appbar, Menu } from 'react-native-paper';
import Icon from 'react-native-vector-icons/FontAwesome5';
import CustomHeader from '../components/Customheader';

import Parches from '../components/Parches';

export default function MainScreen() {
  const statusBarHeight = StatusBar.currentHeight || 0;

  return (
    <View style={styles.container}>
      <View style={[styles.header, { marginTop: statusBarHeight }]}>
        <CustomHeader />
      </View>
      <View style={styles.parches}>
        <Parches />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    elevation: 4, // Sombra para la barra de encabezado
    zIndex: 1, // Coloca la barra de encabezado encima de otros elementos
    flex: 1,
  },
  header: {
    flex: 4, // 40% del alto de la pantalla
  },
  parches: {
    flex: 6, // 60% del alto de la pantalla
  },
});
