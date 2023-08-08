import React, { useState } from 'react';
import { View, StyleSheet, TouchableOpacity } from 'react-native';
import CalendarPicker from 'react-native-calendar-picker';
import { TextInput, Button, Text } from 'react-native-paper';

const DatePickerInput = ({ onStartDateChange, onEndDateChange }) => {
  const [selectedStartDate, setSelectedStartDate] = useState(null);
  const [selectedEndDate, setSelectedEndDate] = useState(null);
  const [showCalendar, setShowCalendar] = useState(false);
  const [editingDate, setEditingDate] = useState(null);

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getUTCFullYear();
    const month = date.getUTCMonth() + 1; // El mes es base 0, entonces sumamos 1
    const day = date.getUTCDate();

    return `${day}-${month}-${year}`;
  };

  const handleDateChange = (date) => {
    if (!selectedStartDate || editingDate === 'start') {
      setSelectedStartDate(date);
      setEditingDate(null);
      onStartDateChange(date);
    } else if (!selectedEndDate || editingDate === 'end') {
      setSelectedEndDate(date);
      setEditingDate(null);
      onEndDateChange(date);
    }
  };

  const handleEditStartDate = () => {
    setEditingDate('start');
    setShowCalendar(true);
  };

  const handleEditEndDate = () => {
    setEditingDate('end');
    setShowCalendar(true);
  };

  return (
    <View style={styles.container}>
      <TextInput
        label="Fecha de Inicio"
        value={
          selectedStartDate && editingDate !== 'start'
            ? formatDate(selectedStartDate)
            : ''
        }
        onFocus={handleEditStartDate}
        style={styles.input}
      />
      <TextInput
        label="Fecha de Fin"
        value={
          selectedEndDate && editingDate !== 'end'
            ? formatDate(selectedEndDate)
            : ''
        }
        onFocus={handleEditEndDate}
        style={styles.input}
      />

      {showCalendar && (
        <View>
          <CalendarPicker
            startFromMonday={true}
            allowRangeSelection={false}
            todayBackgroundColor="#f2e6ff"
            selectedDayColor="#7300e6"
            selectedDayTextColor="#FFFFFF"
            onDateChange={handleDateChange}
          />
          <TouchableOpacity onPress={() => setShowCalendar(false)}>
            <Button mode="contained" style={styles.okButton}>
              OK
            </Button>
          </TouchableOpacity>
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 20,
  },
  input: {
    marginBottom: 20,
  },
  okButton: {
    marginTop: 20,
  },
});

export default DatePickerInput;
