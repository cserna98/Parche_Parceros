import React, { useState, useEffect } from 'react';
import { View, StyleSheet } from 'react-native';
import { Text ,TextInput, Button } from 'react-native-paper';
import { useNavigation } from "@react-navigation/native";
import CalendarPicker from 'react-native-calendar-picker';
import DatePickerInput from '../components/DatePickerInput';
import { postParche } from '../api/ParcheApi';
import StatusBarMargin from '../components/StatusBarMargin';
import { useAuth } from '../Context/AuthContext';


const AddParche = (props) => {
  const [nombre, setNombre] = useState('');
  const [fecha, setFecha] = useState(new Date());
  const [dias, setDias] = useState('');
  const [selectedStartDate, setSelectedStartDate] = useState(null);
  const [selectedEndDate, setSelectedEndDate] = useState(null);
  const [asistentes, setAsistentes] = useState([]); // Array de nombres de asistentes
  const [parcheCreado, setParcheCreado] = useState(false);
  const { token, setAuthToken } = useAuth();



  const {
    navigation,
    route: {params},
  } = props;

  const navigate = useNavigation();

  const handleStartDateChange = (date) => {
    console.log("Selected Start Date:", date);
    setSelectedStartDate(date);
    updateDias(date, selectedEndDate);
  };
  
  const handleEndDateChange = (date) => {
    console.log("Selected end Date:", date);
    setSelectedEndDate(date);
    updateDias(selectedStartDate, date);
  };

 
  

  const updateDias = (startDate, endDate) => {
    if (startDate && endDate) {
      const timeDiff = new Date(endDate).getTime() - new Date(startDate).getTime() + 1;
      const daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
      setDias(daysDiff.toString());
    } else {
      setDias('');
    }
    console.log(dias)
  };

  const handleCrearParche = () => {

   
    const parche = {
      nombre: nombre,
      fechaInicio: new Date(selectedStartDate),
      fechaFin: new Date(selectedEndDate),
      dias: parseInt(dias),
      nombresAsistentes: asistentes, // Asignar el array de asistentes al parcheDTO
    };
    console.log(parche)
    postParche(parche,token)
      .then(() => {
        setParcheCreado(true);
        setTimeout(() => {
          setParcheCreado(false);
          navigate.navigate('Parches'); // Navega a ParchesInfo
        }, 1000);
      })
      .catch((error) => {
        console.error('Error al crear el parche', error);
      });

    // Aquí puedes hacer el POST con el JSON de parcheDTO al backend...
  };

  const handleAgregarAsistente = () => {
    // Agregar un nuevo asistente al array de asistentes
    setAsistentes([...asistentes, '']);
  };

  const handleChangeAsistente = (index, value) => {
    // Actualizar el nombre del asistente en el array de asistentes
    const nuevosAsistentes = [...asistentes];
    nuevosAsistentes[index] = value;
    setAsistentes(nuevosAsistentes);
  };

  return (
    <StatusBarMargin>
      <View style={styles.container}>
      <TextInput
        label="Nombre"
        value={nombre}
        onChangeText={text => setNombre(text)}
        style={styles.input}
      />
      <DatePickerInput onStartDateChange={handleStartDateChange} onEndDateChange={handleEndDateChange}/>
      <TextInput
        label="Días"
        value={dias}
        onChangeText={text => setDias(text)}
        keyboardType="numeric"
        style={styles.input}
      />
      {asistentes.map((asistente, index) => (
        <TextInput
          key={index.toString()}
          label={`Asistente ${index + 1}`}
          value={asistente}
          onChangeText={text => handleChangeAsistente(index, text)}
          style={styles.input}
        />
      ))}
      
      <Button mode="contained" onPress={handleAgregarAsistente} style={styles.button}>
        Añadir Asistente
      </Button>
      <Button mode="contained" onPress={handleCrearParche} style={styles.button}>
        Crear Parche
      </Button>
      {parcheCreado && (
          <View style={styles.messageContainer}>
            <Text style={styles.message}>Parche creado con éxito</Text>
          </View>
        )}
      </View>
    </StatusBarMargin>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    justifyContent: 'center',
  },
  input: {
    marginBottom: 20,
  },
  dateInput: {
    alignItems: 'flex-start',
    borderWidth: 0,
  },
  button: {
    marginTop: 20,
  },
  messageContainer: {
    position: 'absolute',
    top: '33%', // Aproximadamente un tercio del alto de la pantalla
    left: '10%', // 80% del ancho de la pantalla
    right: '10%',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.7)',
    padding: 20,
    borderRadius: 10,
    zIndex: 1000,
  },
  message: {
    color: 'white',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});

export default AddParche;