import React, { useState } from 'react';
import { View, Text, StyleSheet, Dimensions, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome'; // Asegúrate de haber instalado FontAwesome

const CustomHeader = () => {
  const [menuVisible, setMenuVisible] = useState(false);

  // Función para manejar la acción del icono de usuario
  const handleUserIconPress = () => {
    // Agrega aquí la lógica para manejar la acción del icono de usuario
  };

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <View style={styles.userContainer}>
          <TouchableOpacity onPress={handleUserIconPress}>
            <Icon name="user-circle" size={300} style={styles.userIcon} />
          </TouchableOpacity>
          <Text style={styles.username}>Nombre de Usuario</Text>
        </View>
        <TouchableOpacity
          style={styles.menuButton}
          onPress={() => setMenuVisible(!menuVisible)}
        >
          <Icon name="ellipsis-v" size={24} style={styles.menuIcon} />
        </TouchableOpacity>
      </View>
      {menuVisible && (
        <View style={styles.menu}>
          <TouchableOpacity onPress={() => console.log('Opción 1')}>
            <Text style={styles.menuItem}>Opción 1</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={() => console.log('Opción 2')}>
            <Text style={styles.menuItem}>Opción 2</Text>
          </TouchableOpacity>
          {/* Agrega más opciones según sea necesario */}
        </View>
      )}
    </View>
  );
  
};

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    width: '100%', // Color de fondo
  },

  userContainer: {
    alignItems: 'center',
    justifyContent: 'center', // Centra verticalmente
    flex: 1, // Esto hace que el contenido se expanda verticalmente
  },
  menuButton: {
    position: 'absolute',
    top: 8, // Ajusta la posición vertical según sea necesario
    right: 16, // Ajusta la posición horizontal según sea necesario
    zIndex: 2, // Coloca el botón del menú encima del encabezado
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 8,
    paddingHorizontal: 16,
  },
  userIcon: {
    color: 'black', // Color del icono de usuario
  },
  username: {
    fontSize: 18,
    fontWeight: 'bold',
  },
  menuIcon: {
    color: 'black', // Color del icono de menú
  },
  menu: {
    backgroundColor: 'white',
    marginTop: 8,
    elevation: 4, // Sombra para el menú
  },
  menuItem: {
    paddingVertical: 12,
    paddingHorizontal: 16,
    fontSize: 16,
  },
});

export default CustomHeader;