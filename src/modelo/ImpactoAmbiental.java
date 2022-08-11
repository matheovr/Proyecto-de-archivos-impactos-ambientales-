/*
 * Descripción de la clase
 */
package modelo;

/**
 * @author Ana Vega
 * @author Nicolás Carmona Cardona
 * @author Mateo Velasquez 
 * @author Jose D. Gómez
 * @author Daniel Cano
 * @author Juan P. Arango
 */
public class ImpactoAmbiental {
    //Atributos
    private String proposito;
    private String sede;
    private String actividadAsociada;
    private String cicloDeVida;
    private String observaciones;
    private String legislacionAsociada;
    private String controlOperacional;
    private String accionesDeMejora;
    private String situacion;
    private String aspectoAmbiental;
    private String impactoAmbiental;
    private String recursoAfectado;
    private String tipoDelImpacto;
    private String calificacion;
    private int alcance;
    private int probabilidad;
    private int duracion;
    private int recuperabilidad;
    private int magnitud;
    private int normatividad;
    private int importanciaDelImpacto;
    private boolean cumpleNormatividad;
    private boolean valor;
    //private Imagen registroFotografico;

    /**
     * Constructor Vacio
     */
    public ImpactoAmbiental() {
    }
    
    /**
     * Constructor full
     * 
     * @param proposito
     * @param sede
     * @param actividadAsociada
     * @param cicloDeVida
     * @param observaciones
     * @param legislacionAsociada
     * @param controlOperacional
     * @param accionesDeMejora
     * @param situacion
     * @param aspectoAmbiental
     * @param impactoAmbiental
     * @param recursoAfectado
     * @param tipoDelImpacto
     * @param calificacion
     * @param alcance
     * @param probabilidad
     * @param duracion
     * @param recuperabilidad
     * @param magnitud
     * @param normatividad
     * @param importanciaDelImpacto
     * @param cumpleNormatividad
     * @param valor 
     */
    public ImpactoAmbiental(String proposito, String sede, String actividadAsociada, String cicloDeVida, String observaciones, String legislacionAsociada, String controlOperacional, String accionesDeMejora, String situacion, String aspectoAmbiental, String impactoAmbiental, String recursoAfectado, String tipoDelImpacto, String calificacion, int alcance, int probabilidad, int duracion, int recuperabilidad, int magnitud, int normatividad, int importanciaDelImpacto, boolean cumpleNormatividad, boolean valor) {
        this.proposito = proposito;
        this.sede = sede;
        this.actividadAsociada = actividadAsociada;
        this.cicloDeVida = cicloDeVida;
        this.observaciones = observaciones;
        this.legislacionAsociada = legislacionAsociada;
        this.controlOperacional = controlOperacional;
        this.accionesDeMejora = accionesDeMejora;
        this.situacion = situacion;
        this.aspectoAmbiental = aspectoAmbiental;
        this.impactoAmbiental = impactoAmbiental;
        this.recursoAfectado = recursoAfectado;
        this.tipoDelImpacto = tipoDelImpacto;
        this.calificacion = calificacion;
        this.alcance = alcance;
        this.probabilidad = probabilidad;
        this.duracion = duracion;
        this.recuperabilidad = recuperabilidad;
        this.magnitud = magnitud;
        this.normatividad = normatividad;
        this.importanciaDelImpacto = importanciaDelImpacto;
        this.cumpleNormatividad = cumpleNormatividad;
        this.valor = valor;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getActividadAsociada() {
        return actividadAsociada;
    }

    public void setActividadAsociada(String actividadAsociada) {
        this.actividadAsociada = actividadAsociada;
    }

    public String getCicloDeVida() {
        return cicloDeVida;
    }

    public void setCicloDeVida(String cicloDeVida) {
        this.cicloDeVida = cicloDeVida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLegislacionAsociada() {
        return legislacionAsociada;
    }

    public void setLegislacionAsociada(String legislacionAsociada) {
        this.legislacionAsociada = legislacionAsociada;
    }

    public String getControlOperacional() {
        return controlOperacional;
    }

    public void setControlOperacional(String controlOperacional) {
        this.controlOperacional = controlOperacional;
    }

    public String getAccionesDeMejora() {
        return accionesDeMejora;
    }

    public void setAccionesDeMejora(String accionesDeMejora) {
        this.accionesDeMejora = accionesDeMejora;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getAspectoAmbiental() {
        return aspectoAmbiental;
    }

    public void setAspectoAmbiental(String aspectoAmbiental) {
        this.aspectoAmbiental = aspectoAmbiental;
    }

    public String getImpactoAmbiental() {
        return impactoAmbiental;
    }

    public void setImpactoAmbiental(String impactoAmbiental) {
        this.impactoAmbiental = impactoAmbiental;
    }

    public String getRecursoAfectado() {
        return recursoAfectado;
    }

    public void setRecursoAfectado(String recursoAfectado) {
        this.recursoAfectado = recursoAfectado;
    }

    public String getTipoDelImpacto() {
        return tipoDelImpacto;
    }

    public void setTipoDelImpacto(String tipoDelImpacto) {
        this.tipoDelImpacto = tipoDelImpacto;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(int probabilidad) {
        this.probabilidad = probabilidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getRecuperabilidad() {
        return recuperabilidad;
    }

    public void setRecuperabilidad(int recuperabilidad) {
        this.recuperabilidad = recuperabilidad;
    }

    public int getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(int magnitud) {
        this.magnitud = magnitud;
    }

    public int getNormatividad() {
        return normatividad;
    }

    public void setNormatividad(int normatividad) {
        this.normatividad = normatividad;
    }

    public int getImportanciaDelImpacto() {
        return importanciaDelImpacto;
    }

    public void setImportanciaDelImpacto(int importanciaDelImpacto) {
        this.importanciaDelImpacto = importanciaDelImpacto;
    }

    public boolean isCumpleNormatividad() {
        return cumpleNormatividad;
    }

    public void setCumpleNormatividad(boolean cumpleNormatividad) {
        this.cumpleNormatividad = cumpleNormatividad;
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    /**
     * ToString permite crear una cadena de texto que contenga la infomación de la clase
     * 
     * @return retorna el String que reune todas las propiedades de la clase
     */
    @Override
    public String toString() {
        return "ImpactoAmbiental{" + "proposito=" + proposito + ", sede=" + sede + ", actividadAsociada=" + actividadAsociada + ", cicloDeVida=" + cicloDeVida + ", observaciones=" + observaciones + ", legislacionAsociada=" + legislacionAsociada + ", controlOperacional=" + controlOperacional + ", accionesDeMejora=" + accionesDeMejora + ", situacion=" + situacion + ", aspectoAmbiental=" + aspectoAmbiental + ", impactoAmbiental=" + impactoAmbiental + ", recursoAfectado=" + recursoAfectado + ", tipoDelImpacto=" + tipoDelImpacto + ", calificacion=" + calificacion + ", alcance=" + alcance + ", probabilidad=" + probabilidad + ", duracion=" + duracion + ", recuperabilidad=" + recuperabilidad + ", magnitud=" + magnitud + ", normatividad=" + normatividad + ", importanciaDelImpacto=" + importanciaDelImpacto + ", cumpleNormatividad=" + cumpleNormatividad + ", valor=" + valor + '}';
    }
    
}