import { createVuetify } from 'vuetify';

import colors from 'vuetify/util/colors';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';

const darkTheme = {
  dark: true,
  colors: {
    primary: colors.blue.darken4,
    secondary: colors.blue.lighten4,
    error: '#B00020',
    info: '#2196F3',
    success: '#4CAF50',
    warning: '#FB8C00'
  }
};

export default createVuetify({
  components,
  directives,
  theme: {
    defaultTheme: 'darkTheme',
    themes: {
      darkTheme
    }
  }
});
