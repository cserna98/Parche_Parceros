import React from 'react';
import { View, StatusBar, Platform } from 'react-native';

const StatusBarMargin = ({ children }) => {
  return (
    <View
      style={{
        flex: 1,
        marginTop: Platform.OS === 'android' ? StatusBar.currentHeight : 0,
      }}
    >
      {children}
    </View>
  );
};

export default StatusBarMargin;
