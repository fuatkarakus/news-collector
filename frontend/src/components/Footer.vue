<template>
  <v-footer dark padless>
    <v-card flat tile class="indigo lighten-1 white--text text-center">
      <v-card-text>
        <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
          <v-icon size="24px">{{ icon }}</v-icon>
        </v-btn>
      </v-card-text>

      <v-card-text class="white--text pt-0">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Morbi id orci mauris. Mauris tristique maximus purus et rhoncus.
        Ut ultricies arcu eget quam sollicitudin accumsan.
        Ut nec dolor eget purus iaculis consectetur.
        Curabitur consectetur nulla vel massa convallis aliquet.
        Aenean vestibulum cursus massa vel placerat.
        Vivamus sagittis malesuada ipsum, porta euismod ipsum suscipit sit amet.
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        {{ new Date().getFullYear() }} — <strong>Fuat Karakus</strong>
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  data: () => ({
    icons: ['mdi-facebook', 'mdi-twitter', 'mdi-linkedin', 'mdi-instagram']
  })
};
</script>

<style scoped></style>
