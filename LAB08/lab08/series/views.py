from rest_framework.views import APIView
from rest_framework import generics
from rest_framework.response import Response
from .models import Serie
from .serializer import SerieSerializer

# Vista para comprobar que el servidor está activo
class IndexView(APIView):
    def get(self, request):
        context = {'mensaje': 'servidor activo'}
        return Response(context)

# Vista para listar y crear series
class SeriesView(generics.ListCreateAPIView):
    queryset = Serie.objects.all()
    serializer_class = SerieSerializer

# Vista para obtener, actualizar y eliminar una serie en detalle
class SerieDetailView(generics.RetrieveUpdateDestroyAPIView):
    queryset = Serie.objects.all()
    serializer_class = SerieSerializer
    lookup_field = 'pk'  # 'pk' es el campo que se usa por defecto como id
